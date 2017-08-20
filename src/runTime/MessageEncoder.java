/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runTime;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

public class MessageEncoder implements Encoder<Message, String>{
    //@Override
    public String encode(Message message) {
        return new JSONObject(message).toString();
    }
}
