
FROM  java:8

# 把jar包添加到镜像中
ADD wiremock-jre8-standalone-2.29.0.jar  /app.jar


# 镜像暴露的端口
EXPOSE 8808

RUN bash -c 'touch /app.jar'

# 容器启动命令
#ENTRYPOINT ["java","-jar","/app.jar"]

CMD java -jar app.jar


# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone