import java.util.Scanner;

class brickbreak{

    public static void display(Character[][] matrix, int matSize){
        for(int i = 0; i<matSize; i++){
            for(int j = 0; j<matSize; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int travelLeft(int i, int j, Character[][] matrix, int brickCount){
        if(brickCount == 0){
            return 0;
        }
            while(i>0 && j>0){
                if( matrix[i][j] == 'g' || matrix[i][j] == 'w'){
                    break;
                }
                if(matrix[i][j] == 'b'){
                    matrix[i][j] = ' ';
                    brickCount-=1;
                    break;
                }
                i-=1;
                j-=1;
            }
        if(brickCount == 0) return 0;
        while (i>0 && j<matrix.length-1) {
            if(matrix[i][j] == 'b'){
                matrix[i][j] = ' ';
                brickCount-=1;
                break;
            }
            i-=1;
            j+=1;
        }
        display(matrix, matrix.length);
        System.out.println("********************************");

        return brickCount;
    }

    public static int travelRight(int i, int j, Character[][] matrix, int brickCount){
        while (i>0 && j<matrix.length-1) {
            if(matrix[i][j] == 'g' || matrix[i][j] == 'w'){
                break;
            }
            if(matrix[i][j] == 'b'){
                matrix[i][j] = ' ';
                brickCount-=1;
                break;
            }
            i-=1;
            j+=1;
        }
        if( brickCount == 0 ){
            return 0;
        }
        else{
            while(i<matrix.length-1 && j>0 && i>0){
                if(matrix[i][j] == 'w' && matrix[i][j] == 'g'){
                    break;
                }
                if(matrix[i][j] == 'b'){
                    matrix[i][j] = ' ';
                    brickCount-=1;
                    break;
                }
                i-=1;
                j-=1;
            }
        }
        display(matrix, matrix.length);
        System.out.println("********************************");

        return brickCount;
    }

    public static int travelUp(int i, int j, Character[][] matrix, int brickCount){
        if(brickCount==0){
            return 0;
        }
        else{
            while(i>0 && j>0){
                if(matrix [i][j] == 'g' || matrix[i][j]=='w'){
                    break;
                }
                if(matrix[i][j] == 'b'){
                    brickCount-=1;
                    matrix[i][j] = ' ';
                    break;
                }
                i-=1;
            }
        }
        display(matrix, matrix.length);
        return brickCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter the size of the matrix : ");
            int matSize = sc.nextInt();
            Character[][] matrix = new Character[matSize][matSize];
            for(int i = 0; i<matSize; i++){
                for(int j = 0; j<matSize; j++){
                    matrix[i][j] = ' ';
                }
            }
            for(int i = 0; i<matSize; i++){
                matrix[0][i] = 'w';
                matrix[i][0] = 'w';
                matrix[i][matSize-1] = 'w';
            }
            for(int i = 1; i<matSize-1; i++){
                matrix[matSize-1][i] = 'g';
            }
            matrix[matSize-1][matSize/2] = 'o';

            display(matrix, matSize);

            System.out.println("Enter how many bricks has to be placed :");
            int noOfBricks = sc.nextInt();
            while(noOfBricks<0 && noOfBricks>((matSize*matSize)-(matSize*4))){
                System.out.println("Please enter a valid number.");
                noOfBricks = sc.nextInt();
            }
            int ind1 = -1, ind2 = -1;
            for(int i = 0; i<noOfBricks; i++){
                System.out.println("Enter the position of the brick :");
                ind1 = sc.nextInt();
                ind2 = sc.nextInt();
                while(true){
                    while( ind1 < 0 || ind1 > (matSize-2) || ind2 < 0 || ind2 > (matSize-2)){
                        System.out.println("Please enter a valid range from 1 to "+(matSize-2));
                        ind1 = sc.nextInt();
                        ind2 = sc.nextInt();
                    }
                    if(matrix[ind1][ind2] == ' '){
                        break;
                    }
                }
                matrix[ind1][ind2] = 'b';
            }

            display(matrix, matSize);
            int life = noOfBricks;
            while(life>0){
                int start = matSize/2;
                System.out.println("Enter the position to travel : (lf or st or rt)");
                String pos = sc.next();
                int remBrick = 0;
                life--;
                if(noOfBricks==0){
                    System.out.println("Congratulations!!! ALL BRICKS ARE BROKEN....");
                }
                if(pos.equals("lf")){
                    remBrick = travelLeft(matSize-1, start, matrix, noOfBricks);
                    noOfBricks = remBrick;
                }
                else if(pos.equals("st")){
                    remBrick = travelUp(matSize-1, start, matrix, noOfBricks);
                    noOfBricks = remBrick;
                }
                else if(pos.equals("rt")){
                    remBrick = travelRight(matSize-1, start, matrix, noOfBricks);
                    noOfBricks = remBrick;
                }
                System.out.println("Number of balls available : "+life);
            }
            if(noOfBricks == 0 ){
                System.out.println("YOU WON !!!!");
            }
            else if(noOfBricks >0 && life == 0){
                System.out.println("You Lose!!!");
            }
            System.out.println("Do you want to play again (y or n) : ");
            String input = sc.next();
            if(input == "n"){
                System.exit(1);
            }
        }
    }
}