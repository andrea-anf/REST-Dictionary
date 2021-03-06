import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("dictionary")
public class DictionaryService {

    //restituisce il dizionario
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDictionary(){
        return Response.ok(Dictionary.getInstance()).build();
    }

    //restituisce la definizione di una parola
    @Path("get/{word}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDefinition(@PathParam("word") String word){
        Word w = Dictionary.getInstance().getByWord(word);
        if(w != null){
            return Response.ok(w.getDefinition()).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("addWord")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addWord(Word word){
        Word w = Dictionary.getInstance().getByWord(word.getWord());
        if(w == null) {
            if (Dictionary.getInstance().addWord(word)) {
                return Response.ok(word).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    @Path("modifyWord")
    @POST
    @Produces({"application/json", "application/xml"})
    public Response modifyWord(Word word){
        Word w = Dictionary.getInstance().getByWord(word.getWord());
        if(w != null){
            Dictionary.getInstance().modifyDescription(word);
            return Response.ok(word).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("deleteWord/{word}")
    @POST
    @Produces({"application/json", "application/xml"})
    public Response deleteWord(@PathParam("word")String word){
        Word w = Dictionary.getInstance().getByWord(word);
        if(w != null){
            Dictionary.getInstance().deleteWord(w);
            return Response.ok(word + " deleted").build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
