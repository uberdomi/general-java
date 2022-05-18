public class CRS {
    public static int num (String s) throws IllegalArgumentException{
        // =b mod a <=> b a;b a;b a
        String[] pairs = s.split(";");
        int h = pairs.length;
        int[] a = new int[h];
        int[] b = new int[h];
        for(int i=0; i<h; i++){
            String[] pair = pairs[i].split(" ");
            b[i] = parseInt(pair[0]);
            a[i] = parseInt(pair[1]);
            if(a[i]==0||a[i]==1){
                throw new IllegalArgumentException("mod 0/mod 1");
            }
        }
        int sum=b[0];
        int mod = a[0];
        for(int i=1; i<h; i++){
            for(int j=0; j<a[i]; j++){
                if(sum%a[i]==b[i]){
                    break;
                }
                sum+=mod;
            }
            if(sum%a[i]!=b[i]){
                return -1;
            }
            mod*=a[i];
        }
        return sum;
    }

    static int parseInt (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        int result=0;
        int mul=1;
        for(int i=s.length()-1; i>=0; i--){
            int num = s.charAt(i) - '1' + 1;
            if(num=='0'-'1'+1){
                num=0;
            }
            if(num<0||num>9){
                if(i==0&&s.charAt(i)=='-'){
                    result = -result;
                }
                else{
                    throw new IllegalArgumentException("NAN");
                }
            }
            else{
                result += num*mul;
                mul*=10;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(num("5 9;4 8;0 7"));
    }
}
