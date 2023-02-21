FROM centos:7

ENV PATH=$PATH:/opt/java/zulu17.40.19-ca-jdk17.0.6-linux_x64/bin

# 更新软件
RUN yum -y upgrade
# 安装中文包
RUN yum install -y kde-l10n-Chinese
# 重新安装glibc-common
RUN yum -y reinstall glibc-common
# 编译生成语言库
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
# 设置语言默认值为中文，时区改为东八区
RUN echo 'LANG="zh_CN.UTF-8"' > /etc/locale.conf
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8

RUN mkdir /opt/java && \
    curl https://cdn.azul.com/zulu/bin/zulu17.40.19-ca-jdk17.0.6-linux_x64.tar.gz | tar -xz -C /opt/java/


ADD ./src/main/resources/shared /app/shared
ADD ./src/main/resources/*.yml /app
ADD ./target/config.jar /app/
CMD ["java", "-XX:+UseZGC","-Xmx200m", "-Dspring.config.location=/app/", "-Xbootclasspath/a:/app", "-jar", "/app/config.jar"]

EXPOSE 8888