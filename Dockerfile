FROM java:8
LABEL maintainer=“fuxf@sit.com.cn”
LABEL version=“1.0”
LABEL description=“This is an application for uploading baoshan's device info data!"
WORKDIR /opt/yjydUpload
COPY ./target/yjyd-upload.jar .
ENTRYPOINT java -jar yjyd-upload.jar