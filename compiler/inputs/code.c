int teste(float a);
int ggeasy(int k, float b);

int main() {
   int a = 5;
   int b = 6;
   int c = teste(5.0, 6);
   
   return 0; 
}

int teste(float a) {
	int b = 4;
	int k = ggeasy(4,6.0);
	return a;
}

int ggeasy(int a, float b) {
	return a + b;
}


