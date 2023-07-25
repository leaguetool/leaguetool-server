# 基础镜像，openjkd使用8版本
FROM openjdk:8
# 作者
MAINTAINER lemon 83727128@qq.com
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

# 设置项目名称
ENV PROJECT_NAME leaguetool-server
ENV PROJECT_PATH /home/$PROJECT_NAME

WORKDIR $PROJECT_PATH
# 应用构建成功后的jar文件被复制到镜像内 路径是WORKDIR目录下
COPY target/leaguetool-server-0.0.1-SNAPSHOT.jar .
# 启动容器时的进程
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","$PROJECT_PATH/leaguetool-server.jar"]
# 暴露9999端口
EXPOSE 9999 10001