package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.DetectBodyCountRequest;
import com.aliyuncs.facebody.model.v20191230.DetectBodyCountResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

/**
 * 人体计数
 *
 * @author Shadowalker
 */
public class DetectBodyCountDemo {

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) {
        String detectBodyCountURL = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/multiplayer.jpg";
        detectBodyCount(detectBodyCountURL);
    }

    /**
     * 人体计数
     *
     * @param imageURL 图片URL地址
     */
    private static void detectBodyCount(String imageURL) {
        DetectBodyCountRequest detectBodyCountRequest = new DetectBodyCountRequest();
        detectBodyCountRequest.setImageURL(imageURL);
        try {
            DetectBodyCountResponse detectBodyCountResponse = client.getAcsResponse(detectBodyCountRequest);
            System.out.println("人体计数：");
            System.out.println(new Gson().toJson(detectBodyCountResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}