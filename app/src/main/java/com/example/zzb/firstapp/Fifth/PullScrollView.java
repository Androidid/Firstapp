package com.example.zzb.firstapp.Fifth;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.example.zzb.firstapp.R;

/**
 * Created by LiuGuoJie on 2016/4/9.
 * �ο��Զ������PullScroll
 * ����attribute�Ϳ���ScrollView��Ҫ����ʲô  2016.4.8
 * ���һ���ֱ�д
 */
public class PullScrollView extends ScrollView {
    private static final float ABOUT_SCROLL=0.5f;//�غ��ڻ����ٶ�

    private static final int TURN_DISTANCE = 100;

    private View Img;//��������ͼƬ

    private int ImgHeight;//ͼƬ�߶�

    private int ImgVisibleHeight;//�ɼ��߶�

    private View contentview;//ScrollView��contestView

    private Rect contntRect=new Rect();//Scroll�ľ���

    private float touchdownY;//�����ĻʱYֵ

    private boolean abletoTouch=false;//�Ƿ�رջ���

    private boolean ismoving=false;//�����ƶ�

    private boolean isTop=false;//�Ƿ��Ƕ���

    private int InitTop, InitBottom;//ͼƬ��ʼʱ�����͵ײ�

    private int CurrentTop, CurrentBottom;//ͼƬ��ǰ�����͵ײ�

    private OnTurnListener onturnlistener;//���ñ仯������

    private enum State{ //�趨��״̬
        UP,DOWN,NORMAL
    }

    private State nowState= State.NORMAL;

    /*���ֹ��캯��*/
    public PullScrollView(Context context){
        super(context);
        init(context,null);
    }
    public PullScrollView(Context context,AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);
    }
    public PullScrollView(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attr){
        Log.d("Liar","OK");
        setOverScrollMode(OVER_SCROLL_NEVER);//���ù���Ч��
        if(attr!=null){
            TypedArray myArray=context.obtainStyledAttributes(attr, R.styleable.PullScrollView);//��ȡ��Щ����

            if(myArray !=null) {//��ͼƬ����
                ImgHeight=(int)myArray.getDimension(R.styleable.PullScrollView_ImgHeight,-1);
                ImgVisibleHeight=(int)myArray.getDimension(R.styleable.PullScrollView_ImgVisibleHeight,-1);
                myArray.recycle();//����
            }
        }
    }

    public void setImg(View v){
        Img=v;
    }

    public void setOnturnlistener(OnTurnListener onturnlistener){
        this.onturnlistener=onturnlistener;
    }

    @Override
    protected void onFinishInflate(){//��findIdBy��ɺ����
        if(getChildCount()>0){//������һ��
            contentview=getChildAt(0);
        }
    }

    @Override
    protected void onScrollChanged(int l,int t,int oldl,int oldt){
        super.onScrollChanged(l,t,oldl,oldt);
        if(getScaleY()==0){
            isTop=true;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            touchdownY = ev.getY();
            CurrentTop = InitTop = Img.getTop();
            CurrentBottom = InitBottom = Img.getBottom();
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (contentview != null) {
            doTouchEvent(ev);//�������˳�����������ȥ����
        }
        return abletoTouch || super.onTouchEvent(ev);//�Ƿ񻬶�
    }

    private void doTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_MOVE://������UP DOWN
                doActionMove(event);//������
                break;

            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {//�Ƿ���Ҫ����
                    rollBackAnimation();
                }

                if (getScrollY() == 0) {
                    // ������������ʱ����״̬����Ϊ�����������������϶��������϶������˺��״δ�������Ӧ������
                    nowState = State.NORMAL;
                }

                ismoving = false;
                abletoTouch = false;
                break;

            default:
                break;
        }
    }

    private void doActionMove(MotionEvent event) {
        // ������������ʱ����״̬����Ϊ�����������������϶��������϶������˺��״δ�������Ӧ������
        if (getScrollY() == 0) {
            nowState = State.NORMAL;

            // ��������������ʼλ��ʱ������Touch down������Ϊ��ǰTouch�������
            if (isTop) {
                isTop = false;
                touchdownY = event.getY();
            }
        }

        float deltaY = event.getY() - touchdownY;

        // �����״�Touch����Ҫ�жϷ�λ��UP OR DOWN
        if (deltaY < 0 && nowState == State.NORMAL) {
            nowState = State.UP;
        } else if (deltaY > 0 && nowState == State.NORMAL) {
            nowState = State.DOWN;
        }

        if (nowState == State.UP) {
            deltaY = deltaY < 0 ? deltaY : 0;

            ismoving = false;
            abletoTouch = false;

        } else if (nowState == State.DOWN) {
            if (getScrollY() <= deltaY) {
                abletoTouch = true;
                ismoving = true;
            }
            deltaY = deltaY < 0 ? 0 : deltaY;
        }

        if (ismoving) {//�ƶ���
            // ��ʼ��content view����
            if (contntRect.isEmpty()) {
                // ���������Ĳ���λ��
                contntRect.set(contentview.getLeft(), contentview.getTop(), contentview.getRight(),
                        contentview.getBottom());
            }

            // ����Img�ƶ�����(�����ƶ��ľ���*����ϵ��*0.5)
            float headerMoveHeight = deltaY * ABOUT_SCROLL;
            CurrentTop = (int) (InitTop + headerMoveHeight);
            CurrentBottom = (int) (InitBottom + headerMoveHeight);

            // ����content�ƶ�����(�����ƶ��ľ���*����ϵ��)
            float contentMoveHeight = deltaY * ABOUT_SCROLL;

            // ����content�ƶ��ľ��룬���ⳬ��Img�ĵױ�Ե
            int headerBottom = CurrentBottom - ImgVisibleHeight;
            int top = (int) (contntRect.top + contentMoveHeight);
            int bottom = (int) (contntRect.bottom + contentMoveHeight);

            if (top <= headerBottom && CurrentTop<=InitTop+TURN_DISTANCE) {
                // �ƶ�content view
                contentview.layout(contntRect.left, top, contntRect.right, bottom);

                // �ƶ�header view
                Img.layout(Img.getLeft(), CurrentTop, Img.getRight(), CurrentBottom);
            }
        }
    }

    private void rollBackAnimation() { //�����ع�����
        CurrentTop= Math.min(InitTop+TURN_DISTANCE,CurrentTop);
        TranslateAnimation tranAnim = new TranslateAnimation(0, 0,
                Math.abs(InitTop - CurrentTop), 0);//����
        tranAnim.setDuration(200);
        Img.startAnimation(tranAnim);

        Img.layout(Img.getLeft(), InitTop, Img.getRight(), InitBottom);

        // �����ƶ�����
        TranslateAnimation innerAnim = new TranslateAnimation(0, 0, contentview.getTop(), contntRect.top);
        innerAnim.setDuration(200);
        contentview.startAnimation(innerAnim);
        contentview.layout(contntRect.left, contntRect.top, contntRect.right, contntRect.bottom);

        contntRect.setEmpty();

        // �ص�������
        if (CurrentTop > InitTop + TURN_DISTANCE && onturnlistener != null){
            onturnlistener.onTurn();
        }
    }

    /**
     * �Ƿ���Ҫ��������
     */
    private boolean isNeedAnimation() {
        return !contntRect.isEmpty() && ismoving;
    }

    public interface  OnTurnListener{
        public void onTurn();
    }
}
