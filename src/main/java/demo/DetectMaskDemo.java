package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.DetectMaskRequest;
import com.aliyuncs.facebody.model.v20191230.DetectMaskResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

/**
 * 可以对输入图片中面积最大的人脸进行口罩检测。
 *
 * @author Shadowalker
 */
public class DetectMaskDemo {

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) {
        String wearMaskSampleImgURL = "https://visionapi-test.oss-cn-shanghai.aliyuncs.com/mask_1.jpg";
        detectMask(wearMaskSampleImgURL);
    }

    /**
     * 口罩识别
     *
     * @param wearMaskSampleImgURL 图片URL地址
     */
    private static void detectMask(String wearMaskSampleImgURL) {
        DetectMaskRequest detectMaskRequest = new DetectMaskRequest();
        detectMaskRequest.setImageURL(wearMaskSampleImgURL);
        try {
            DetectMaskResponse detectMaskResponse = client.getAcsResponse(detectMaskRequest);
            System.out.println("口罩识别：");
            System.out.println(new Gson().toJson(detectMaskResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}