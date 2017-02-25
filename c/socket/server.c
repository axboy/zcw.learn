#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<sys/wait.h>
#include<netinet/in.h>
#include<netdb.h>
#include<arpa/inet.h>
#include<errno.h>

#define MYPORT 9486

int main( void){
	
	int sockListen = socket(AF_INET, SOCK_DGRAM, 0);
	if(sockListen == -1){
		printf("socket fail\n");
		return -1;
	}
	int set = 1;
	//下一行不知道干嘛用
	setsockopt(sockListen, SOL_SOCKET, SO_REUSEADDR, &set, sizeof(int));
	struct sockaddr_in recvAddr;
	//下一行不知道干嘛用
	memset(&recvAddr, 0, sizeof(struct sockaddr_in));
	recvAddr.sin_family = AF_INET;
	recvAddr.sin_port = htons(MYPORT);
	//recvAddr.sin_addr.s_addr = INADDR_ANY;
	recvAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	// 必须绑定，否则无法监听
	//参数介绍:int bind(int sockfd,struct sockaddr*my_addr,int addrlen);
	//第一个参数为调用soctet()返回的套接口文件描述符
	//第二个参数my_addr是指向数据结构sockaddr的指针。数据结构sockaddr中包括了关于你的地址、端口和IP地址的信息。
	//第三个参数addrlen可以设置成sizeof(structsockaddr)。
	int binddd = bind(sockListen, (struct sockaddr *)&recvAddr, sizeof(struct sockaddr));
	if(binddd == -1){
		printf("bind fail\n");
		return -1;
	}
	char recvbuf[128];
	int addrLen = sizeof(struct sockaddr_in);
	printf("-----------\n");
	while(1){
		int recvbytes = recvfrom(sockListen, recvbuf, 128, 0,(struct sockaddr *)&recvAddr, &addrLen);
		if(recvbytes != -1){
			recvbuf[recvbytes] = '\0';
			printf("receive:%s\n", recvbuf);
			char result[128];
			calculate(recvbuf,result);
			printf("result:%s\n\n", result);
			//返回数据
			/**
			int sendBytes = sendto(recvbytes, result, strlen(result), 0,(struct sockaddr *)&recvAddr, sizeof(struct sockaddr));
			if(sendBytes == -1){
				printf("return fail, errno=%d\n", errno);
			}else{				
				printf("return succeed\n");
			}
			*/
			
			//这里使用write向客户端发送信息，也可以尝试使用其他函数实现
			if(-1 == write(recvbytes,result,32)){
				printf("write fail!\r\n");
			}
			else{
				printf("write ok!\r\n");
			}
			close(recvbytes);
		}else{
			printf("recvfrom fail\n");
		}
	}	
	close(sockListen);
	return 0;
}
