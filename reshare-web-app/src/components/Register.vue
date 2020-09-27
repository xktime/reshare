<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="register-page">
    <el-form-item label="账号" prop="account">
      <el-input v-model="ruleForm.account"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password" required>
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass" required>
      <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-radio-group v-model="ruleForm.sex">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="手机号码" prop="phoneNumber" required>
      <el-input v-model="ruleForm.phoneNumber" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="出生日期" required>
      <el-col>
        <el-form-item prop="birthday">
          <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthday"
                          style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item label="所在地区" prop="region" required>
      <el-cascader :options="options" v-model="ruleForm.region"
                   style="width: 100%;"></el-cascader>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="register()">注册</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
    import area from '@/assets/js/select_area.js';

    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value == null || value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value == null || value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                options: area,
                ruleForm: {
                    account: '',
                    password: '',
                    sex: '',
                    phoneNumber: '',
                    birthday: '',
                    region: ''
                },
                rules: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur'}
                    ],
                    phoneNumber: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                        {min: 11, max: 11, message: '手机号码格式不正确', trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    checkPass: [
                        {validator: validatePass2, trigger: 'blur'}
                    ],
                    region: [
                        {required: true, message: '请选择地区', trigger: 'change'}
                    ],
                    birthday: [
                        {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
                    ],
                }
            };
        },
        methods: {
            register() {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        let data = new FormData();
                        data.append("account", this.ruleForm.account);
                        data.append("password", this.ruleForm.password);
                        data.append("birthday", this.ruleForm.birthday);
                        data.append("phoneNumber", this.ruleForm.phoneNumber);
                        let tempSex = this.ruleForm.sex === '男' ? 0 :
                            (this.ruleForm.sex === null || this.ruleForm.sex === '' ? 2 : 1);
                        data.append("sex", tempSex);
                        data.append("region", this.ruleForm.region);
                        const api = this.$apiUrl + 'account/register';
                        this.axios.post(api, data).then(
                            (response) => {
                                if (response.data.code != 200) {
                                    this.$alert(response.data.errorMessage);
                                    return;
                                }
                                this.$store.commit('login', this.ruleForm.account);
                                this.$store.commit('addToken', response.data.data);
                                this.$router.push({path: this.$route.query.redirect || '/'});
                            }
                        );
                    } else {
                        console.log('error submit!');
                        return false;
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
  .register-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>
