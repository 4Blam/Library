package springboot;

public class ErrorDto {
    private String message = "Sorry, internal error has occured";
    public ErrorDto(){};
    public ErrorDto(int index){
        if(index == 1){
            this.message = "Sorry, external error has occured";
        }
    }
}
