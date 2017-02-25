#include <stdio.h>
#include <stdlib.h>

//文件指针
FILE * fileOut ;
char p[9][10] ;
int sum = 0;

//从文件读取待解决的数独题目
void getInput()
{
	int i;
	FILE * fileIn = fopen("./input.txt","r");
	if( !fileIn){
		perror("fopen");
		exit(-1);
	}
	for(i = 0; i < 9; i++){
		fread(p[i], 1, 10, fileIn);
		p[i][9] = '\0';
	}
	fclose(fileIn);
}

//输出日志到文件
void outputFile(int row,int col, int num)
{
	int i;
	fprintf(fileOut,"\nrow=%d col=%d num=%d\n",row+1,col+1,num);
	for(i = 0; i < 9; i++)
	{
		fprintf(fileOut,"%s",p[i]);
	}
}

//输出结果
void output()
{
	int i;
	printf("result %d:\n",sum);
	for(i = 0; i < 9; i++)
		printf("%s\n",p[i]);
	printf("\nresult end");
}

//验证是否符合要求
int validate(int row,int col, int num)
{
	int i, j;
	
	for(i = 0; i < 9; i++)
	{		
		//验证列
		if( p[row][i] == num + '0')
			return 0;
		
		//验证行
		if( p[i][col] == num + '0')
			return 0;
	}
	
	//验证九宫格
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			if( p[row/3*3+i][col/3*3+j] == num+'0')
				return 0;
		}
	}
	
	//一切没问题，返回1
	return 1;
}

void findResult(int row, int col)
{
	//当前方格有值，跳到下一格
	if(p[row][col] != '0'){
		if(col != 8)
			findResult(row,col+1);
		else findResult(row+1,0);
	}else{
		int k;
		for(k=1;k<=9;k++){
			if(validate(row,col,k) == 1){
				p[row][col] = '0' + k;
				//outputFile(row, col, k);	//输出日志到文件
				if(col != 8)
					findResult(row, col+1);
				else if(row != 8)
					findResult(row+1, 0);
				else {
					sum++;
					output();
					return ;
				}
			}
			if(k == 9){
				p[row][col] = '0';
			}
		}
	}
}

int main( void)
{
	getInput();
	//fileOut = fopen("./output.txt","w");
	//fileOut = fopen("./output.txt","a");
	findResult(0, 0);
	//fclose(fileOut);
	return 0;
}