#include<stdio.h>
#include<stdlib.h> 

//判断一个字符串是否为操作符
//返回1表示op是运算符，0则不是
int is_operation( char op)
{
	switch(op){
		case '+' :
		case '-' :
		case '*' :
		case '/' :
			return 1;
		default :
			return 0;
	}
}

//求运算符的优先级
int priority( char op)
{
	switch(op){
		case '#' : return -1;
		case '(' : return 0;
		case '+' :
		case '-' : return 1;
		case '*' :
		case '/' : return 2;
		default : return -1;
	}
}

//将一个中缀表达式e转换为与它等价的后缀表达式f
void postfix(char e[], char f[])
{
	int i = 0,j = 0;
	char opst[128];			//操作符栈
	int top = 0,t;
	opst[top] = '#';top++;
	while(e[i] != '#'){
		if((e[i] >= '0' && e[i] <= '9') || e[i] == '.'){		//匹配到数字和小数点，直接写入后缀表达式
			f[j++] = e[i];
		}
		else if( e[i] == '('){			//匹配到左括号，进入操作符栈
			opst[top] = e[i];
			top++;
		}
		else if( e[i] == ')'){			//匹配到右括号，将其对应的左括号后的操作符全部写入后缀表达式
			t = top - 1;
			while(opst[t] != '('){
				f[j++] = opst[--top];
				t = top - 1;
			}
			top--;				//出栈
		}
		else if( is_operation(e[i])){	//匹配到运算符
			f[j++] = ' ';		//用空格分开两个操作数
			while(priority(opst[top-1]) >= priority(e[i])){
				f[j++] = opst[--top];
			}
			opst[top] = e[i];
			top++;				//当前元素进栈
		}
		i++;				//处理下一个元素
	}
	while(top){				//遍历完，将栈中剩余元素添加到目标数组
		f[j++] = opst[--top];
	}
	f[j] = '\0';			//添加字符串结尾符
}

//将数字字符串转变为对应的数，模拟atof(f)函数
double readNumber( char f[], int *i)
{
	double x = 0.0;
	int k = 0;
	while( f[*i] >= '0' && f[*i] <= '9'){		//处理整数部分
		x = x * 10 + (f[*i] - '0');
		(*i)++;
	}
	if( f[*i] == '.'){			//处理小数部分
		(*i)++;
		while( f[*i] >= '0' && f[*i] <= '9'){
			x = x * 10 + (f[*i] - '0');
			(*i)++;
			k++;
		}
	}
	while( k != 0){
		x /= 10.0;
		k--;
	}
	return x;
}

//后缀表达式求值
double evalpost(char f[])
{
	double obst[128];		//操作数栈
	int top = 0;
	int i = 0;
	double x1,x2;
	while(f[i] != '#'){
		if( f[i] >= '0' && f[i] <= '9'){
			obst[top] = readNumber(f, &i);
			top++;
		}
		else if( f[i] == ' '){
			i++;
		}
		else{
			x2 = obst[--top];
			x1 = obst[--top];
			switch( f[i]){
				case '+' :{
					obst[top] = x1 + x2;
					break;
				}
				case '-' :{
					obst[top] = x1 - x2;
					break;
				}
				case '*' :{
					obst[top] = x1 * x2;
					break;
				}
				case '/' :{
					obst[top] = x1 / x2;
					break;
				}
			}
			i++;top++;			
		}
	}
	return obst[0];
}

//计算函数
void calculate(char input[],char output[])
{
	char f[128] ;
	postfix(input,f);
	double d = evalpost(f);
	sprintf(output, "%lf", d);		//将浮点数转为字符串
}

//测试函数,将test改main即可
void test(int argc, char** argv)
{
	char* msg = argv[1];
	char f[128] ;
	postfix(msg,f);
	printf("result:%s\n",f);
	double d = evalpost(f);
	printf("%f\n",d);
}
