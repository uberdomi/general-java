public class Calculator {
    private String input;
    private int fromRight;
    private boolean[] used;
    private Integer[] results;
    private int at;


    public Calculator(){
        input = "";
    }

    public int calculate(String s){
        input = s;
        fromRight=s.length();
        results = new Integer[fromRight];
        used = new boolean[fromRight];
        for(int i=0; i<fromRight; i++){
            used[i]=false;
            results[i]=null;
        }
        fromRight--;

        while(fromRight>0){
            addResultPara();
        }
        fromRight=s.length()-1;
        while(fromRight>0){
            addResultMulti();
        }
        fromRight=s.length()-1;
        while(fromRight>0){
            addResultPM();
        }

        System.out.println(s + " = " + results[at]);
        return results[at];
    }


    private void addResultPara(){
      //  System.out.println(fromRight);
        while(isInBounds(fromRight)){
            if(input.charAt(fromRight)!='('||used[fromRight]){
                fromRight--;
             //   System.out.println("line: "+ fromRight);
            }
            else{
              //  System.out.println("operation " + input.charAt(fromRight));
                used[fromRight]=true;
                int fromLeft=fromRight;
                int ope=fromRight;

                while(input.charAt(fromLeft)!=')'||used[fromLeft]){
                    fromLeft++;
                }
                used[fromLeft]=true;

                while(!isOperation(input.charAt(ope))||used[ope]){
                    ope++;
                }
                used[ope]=true;

                int right=0;
                int left=0;

                if(!isNumber(input.charAt(ope-1))){
                    left = results[ope-1];
                }
                else{
                    int tener=1;
                    int opel=ope-1;
                    while(opel>fromRight){
                        left += ((int) input.charAt(opel)-48)*tener;

                        opel--;
                        tener*=10;
                    }
                }

                if(!isNumber(input.charAt(fromLeft-1))){
                    right = results[fromLeft-1];
                }
                else{
                    int tener=1;
                    int oper=fromLeft-1;
                    while(oper>ope){
                        right += ((int) input.charAt(oper)-48)*tener;

                        oper--;
                        tener*=10;
                    }
                }

                Integer add=null;

                switch (input.charAt(ope)){
                    case '+' -> add=left+right;
                    case '-' -> add=left-right;
                    case '*' -> add=left*right;
                }

                at=ope;
                results[ope] = add;
                results[fromLeft] = add;
                results[fromRight] = add;
            }
        }
    }

    private void addResultMulti(){
      //  System.out.println(fromRight);
        while(isInBounds(fromRight)){
            if(input.charAt(fromRight)!='*'||used[fromRight]){
                fromRight--;
            //    System.out.println("line: "+ fromRight);
            }
            else{
             //   System.out.println("operation " + input.charAt(fromRight));
                int ope=fromRight;
                int fromLeft=fromRight;
                used[ope]=true;

                int right=0;
                int left=0;

                if(used[ope-1]){
                    left = results[ope-1];
                }
                else{
                    int tener=1;
                    int opel=ope-1;
                    while(isInBounds(opel)&&isNumber(input.charAt(opel))){
                        left += ((int) input.charAt(opel)-48)*tener;

                        opel--;
                        tener*=10;
                    }
                    fromRight=opel+1;
                }

                if(used[ope+1]){
                    right = results[ope+1];
                }
                else{
                    int tener=1;
                    int oper=ope+1;
                    while(isInBounds(oper)&&isNumber(input.charAt(oper))){
                        oper++;
                    }
                    oper--;
                    fromLeft=oper;

                    while(oper>ope){
                        right += ((int) input.charAt(oper)-48)*tener;

                        oper--;
                        tener*=10;
                    }
                }

                Integer add=null;

                switch (input.charAt(ope)){
                    case '+' -> add=left+right;
                    case '-' -> add=left-right;
                    case '*' -> add=left*right;
                }

                used[fromLeft]=true;
                used[fromRight]=true;
                at=ope;
                results[ope] = add;
                results[fromLeft] = add;
                results[fromRight] = add;
            }

        }
    }

    private void addResultPM(){
      //  System.out.println(fromRight);
        while(isInBounds(fromRight)){
            if(input.charAt(fromRight)!='+'&&input.charAt(fromRight)!='-'||used[fromRight]){
                fromRight--;
             //   System.out.println("line: "+ fromRight);
            }
            else{
             //   System.out.println("operation " + input.charAt(fromRight));
                int ope=fromRight;
                int fromLeft=fromRight;
                used[ope]=true;

                int right=0;
                int left=0;

                if(used[ope-1]){
                    left = results[ope-1];
                }
                else{
                    int tener=1;
                    int opel=ope-1;
                    while(isInBounds(opel)&&isNumber(input.charAt(opel))){
                        left += ((int) input.charAt(opel)-48)*tener;

                        opel--;
                        tener*=10;
                    }
                    fromRight=opel+1;
                }

                if(used[ope+1]){
                    right = results[ope+1];
                }
                else{
                    int tener=1;
                    int oper=ope+1;
                    while(isInBounds(oper)&&isNumber(input.charAt(oper))){
                        oper++;
                    }
                    oper--;
                    fromLeft=oper;

                    while(oper>ope){
                        right += ((int) input.charAt(oper)-48)*tener;

                        oper--;
                        tener*=10;
                    }
                }

                Integer add=null;

                switch (input.charAt(ope)){
                    case '+' -> add=left+right;
                    case '-' -> add=left-right;
                    case '*' -> add=left*right;
                }

                used[fromLeft]=true;
                used[fromRight]=true;
                at=ope;
                results[ope] = add;
                results[fromLeft] = add;
                results[fromRight] = add;
            }

        }
    }

    public boolean isOperation(char c){
        switch (c){
            case '+','-','*' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }


    public boolean isNumber(char c){
        switch (c){
            case '0','1','2','3','4','5','6','7','8','9' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public boolean isInBounds(int i){
        return i>=0&&i<input.length();
    }


    public static void main(String[] args) {
        var c = new Calculator();
        //System.out.println(new Calculator("(((4*(35+41))-(123-34))*((23+67)-88))").addAll());
        c.calculate("1+2");
        c.calculate("1+2+3");
        c.calculate("2+2*2");
        c.calculate("(2+2)*2");
        c.calculate("(1+2)*(5-3)");
    }
}
