package edu.curtin.maze;

//acts like PaymentService class
public class Reader{
    private InputReader inputReader;
    private FileInputReader fileInputReader;

    //for reading input from user der
    public void doOption(){
        inputReader.move();
    }

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    //for adding the object into the maze(Grid[][])
    public void addObject(){
        fileInputReader.create();

    }

    public void setFileInputReader(FileInputReader fileInputReader) {
        this.fileInputReader = fileInputReader;
    }
}
