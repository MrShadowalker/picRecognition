package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.RecognizeFaceRequest;
import com.aliyuncs.facebody.model.v20191230.RecognizeFaceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

/**
 * 人脸属性识别
 * 可以识别检测人脸的性别、年龄、表情、眼镜四种属性。返回人脸的1024维深度学习特征，基于这个特征并按照特征比较规则，可实现高性能的人脸识别。
 *
 * @author Shadowalker
 */
public class RecognizeFaceDemo {

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) {
        String recognizeFaceURL = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/recognize_1.jpg";
        recognizeFace(recognizeFaceURL);
    }

    /**
     * 人脸属性识别
     *
     * @param imageURL 图片 URL 地址
     */
    private static void recognizeFace(String imageURL) {
        RecognizeFaceRequest recognizeFaceRequest = new RecognizeFaceRequest();
        recognizeFaceRequest.setImageURL(imageURL);
        try {
            RecognizeFaceResponse recognizeFaceResponse = client.getAcsResponse
                    (recognizeFaceRequest);
            System.out.println("人脸属性识别：");
            System.out.println(new Gson().toJson(recognizeFaceResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}