#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<sys/wait.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<errno.h>

#define MYPORT 9486

int main(int argc, char* argv[]){
	char* msg = argv[1];
	int brdcFd;
	if((brdcFd = socket(PF_INET, SOCK_DGRAM, 0)) == -1){
		printf("socket fail\n");
		return -1;
	}
	int optval = 1;			//这个值一定要设置，否则可能导致sendto()失败
	setsockopt(brdcFd, SOL_SOCKET, SO_BROADCAST | SO_REUSEADDR, &optval, sizeof(int));
	struct sockaddr_in theirAddr;
	memset(&theirAddr, 0, sizeof(struct sockaddr_in));
	theirAddr.sin_family = AF_INET;
	theirAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	theirAddr.sin_port = htons(MYPORT);
	int sendBytes = sendto(brdcFd, msg, strlen(msg), 0,(struct sockaddr *)&theirAddr, sizeof(struct sockaddr));
	if(sendBytes == -1){
		printf("sendto fail, errno=%d\n", errno);
		return -1;
	}
	printf("msg=%s\n", msg);	
	
	
	int recbytes;
	char buffer[128];
	/*连接成功,从服务端接收字符*/
	if(-1 == (recbytes = read(brdcFd,buffer,1024)))
	{
		printf("read data fail !\r\n");
		return -1;
	}
	printf("result:%s\n",buffer);

	buffer[recbytes]='\0';
	printf("%s\r\n",buffer);
	
	
	close(brdcFd);
	return 0;
}
