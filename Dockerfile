# 基础镜像，openjkd使用8版本
FROM openjdk:8
# 作者
MAINTAINER lemon
# 系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
# 声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp
# 应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/leaguetool-server-0.0.1-SNAPSHOT.jar leaguetool-server.jar
# 启动容器时的进程
ENTRYPOINT ["java","-jar","/leaguetool-server.jar"]
# 暴露9999端口
EXPOSE 9999
# 暴露10001端口
EXPOSE 10001