# bst_server

( java -jar bst_server.jar --spring.profiles.active=pro --server.port=8080 &)

开发工具 idea

#################################mysql########################################
wget https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz


#################################java########################################
yum install java-1.8.0-openjdk.x86_64

#################################rz########################################
上传文件命令
yum install lrzsz

#################################nginx########################################
#nginx
 yum -y install gcc pcre-devel zlib-devel openssl openssl-devel
 rpm -qa pcre
 wget http://nginx.org/download/nginx-1.14.2.tar.gz
 tar -xvf nginx-1.14.2.tar.gz
 cd  nginx-1.14.2
 ./configure
 make && make install
 cd /usr/local/nginx/sbin
 ./nginx -t
 ./nginx
 netstat -anp|grep 80
 curl localhost

#################################redis########################################
#redis
1.获取redis资源
wget http://download.redis.io/releases/redis-5.0.5.tar.gz

2.解压
tar xzvf redis-4.0.8.tar.gz

3.安装
　　cd redis-4.0.8

　　make

　　cd src

　　make install PREFIX=/usr/local/redis

4.移动配置文件到安装目录下
　　cd ../

　　mkdir /usr/local/redis/etc

　　mv redis.conf /usr/local/redis/etc

 5.配置redis为后台启动
vi /usr/local/redis/etc/redis.conf //将daemonize no 改成daemonize yes

6.将redis加入到开机启动
　　vi /etc/rc.local //在里面添加内容：/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf (意思就是开机调用这段开启redis的命令)

7.开启redis
　　/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf