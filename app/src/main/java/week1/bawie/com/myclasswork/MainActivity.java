package week1.bawie.com.myclasswork;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.yx_bt)
    Button yxBt;
    @BindView(R.id.yj_bt)
    Button yjBt;
    @BindView(R.id.bl_bt)
    Button blBt;
    @BindView(R.id.gif_bt)
    Button gifBt;
    @BindView(R.id.image_show)
    SimpleDraweeView imageShow;
    @BindView(R.id.huoqu_bt)
    Button huoquBt;
    @BindView(R.id.fanshe_bt)
    Button fansheBt;
    private Unbinder bind;
    public float ratio = 1024/653f;
    @GetName("焦炳翔")
    String name;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initView();//设置初始图片
    }

    private void initView() {
        //http://pic32.nipic.com/20130815/1754547_131807583000_2.jpg
        Uri uri = Uri.parse("http://ww2.sinaimg.cn/large/85cccab3gw1etdghsr1xxg20ci071nlp.jpg");
        imageShow.setImageURI(uri);
        imageShow.setAspectRatio(ratio);
    }

    @OnClick({R.id.yx_bt, R.id.yj_bt, R.id.bl_bt, R.id.gif_bt, R.id.huoqu_bt, R.id.fanshe_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yx_bt:
                RoundingParams params = RoundingParams.asCircle();
                GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
                GenericDraweeHierarchy hierarchy = builder.setRoundingParams(params).build();
                imageShow.setHierarchy(hierarchy);
                imageShow.setImageURI("http://ww2.sinaimg.cn/large/85cccab3gw1etdghsr1xxg20ci071nlp.jpg");
                break;
            case R.id.yj_bt:
                RoundingParams params1 = RoundingParams.fromCornersRadius(7f);
                GenericDraweeHierarchyBuilder builder1 = new GenericDraweeHierarchyBuilder(getResources());
                GenericDraweeHierarchy hierarchy1 = builder1.setRoundingParams(params1).build();
                imageShow.setHierarchy(hierarchy1);
                imageShow.setImageURI("http://ww2.sinaimg.cn/large/85cccab3gw1etdghsr1xxg20ci071nlp.jpg");
                break;
            case R.id.bl_bt:
                imageShow.setImageURI("http://ww2.sinaimg.cn/large/85cccab3gw1etdghsr1xxg20ci071nlp.jpg");
                imageShow.setAspectRatio(1.2f);
                break;
            case R.id.gif_bt:
                Uri uri = Uri.parse("http://ww2.sinaimg.cn/large/85cccab3gw1etdghsr1xxg20ci071nlp.jpg");
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                        .build();
                imageShow.setController(draweeController);
                break;
            case R.id.huoqu_bt:
                Class c = getClass();
                try {
                    Field field = c.getField("name");
                    GetName getName = field.getAnnotation(GetName.class);
                    String value = getName.value();
                    huoquBt.setText(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.fanshe_bt:
                Class c1 = getClass();
                try {
                    Method method = c1.getMethod("addData", int.class);
                    method.invoke(this,1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 给集合添加数据
     * @param data
     */
    public void addData(int data){
        list.add(data+"");
        Toast.makeText(this,list.toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
