#include <stdlib.h>
#include <sys/types.h>
#include <stdio.h>
#include <sys/socket.h>
#include <linux/in.h>
#include <string.h>

#define MYPORT 8888

int main( void)
{
	int sfp,nfp; /* 定义两个描述符 */
	struct sockaddr_in s_add,c_add;
	int sin_size;
	sfp = socket(AF_INET, SOCK_STREAM, 0);
	if(-1 == sfp){
		printf("socket fail ! \r\n");
		return -1;
	}
	printf("socket ok !\r\n");

	/* 填充服务器端口地址信息，以便下面使用此地址和端口监听 */
	bzero(&s_add,sizeof(struct sockaddr_in));
	s_add.sin_family=AF_INET;
	s_add.sin_addr.s_addr=htonl(INADDR_ANY); /* 这里地址使用全0，即所有 */	
	//s_add.sin_addr.s_addr = inet_addr("127.0.0.1");
	s_add.sin_port=htons(MYPORT);
	/* 使用bind进行绑定端口 */
	if(-1 == bind(sfp,(struct sockaddr *)(&s_add), sizeof(struct sockaddr))){
		printf("bind fail !\r\n");
		return -1;
	}
	printf("bind ok !\r\n");
	/* 开始监听相应的端口 */
	if(-1 == listen(sfp,5)){
		printf("listen fail !\r\n");
		return -1;
	}
	printf("listen ok\r\n");

	while(1)
	{
		sin_size = sizeof(struct sockaddr_in);
		nfp = accept(sfp, (struct sockaddr *)(&c_add), &sin_size);
		if(-1 == nfp){
			printf("accept fail !\r\n");
			return -1;
		}
		printf("accept ok!\r\nServer start get connect from %#x : %#x\r\n",ntohl(c_add.sin_addr.s_addr),ntohs(c_add.sin_port));

		/* 这里使用write向客户端发送信息，也可以尝试使用其他函数实现 */
		if(-1 == write(nfp,"hello,welcome to my server \r\n",32)){
			printf("write fail!\r\n");
			return -1;
		}
		printf("write ok!\r\n");
		close(nfp);
	}
	close(sfp);
	return 0;
}
