# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD pump-0.0.1-SNAPSHOT.jar /pump.jar
# 运行过程中创建一个pump.jar文件
RUN bash -c 'touch /pump.jar'
# 声明服务运行在8088端口
EXPOSE 8088
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/pump.jar"]
# 指定维护者的名字
MAINTAINER xuwx