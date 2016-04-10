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
 * 参考自定义组件PullScroll
 * 定义attribute和看了ScrollView需要集成什么  2016.4.8
 * 完成一部分编写
 */
public class PullScrollView extends ScrollView {
    private static final float ABOUT_SCROLL=0.5f;//关乎于滑动速度

    private static final int TURN_DISTANCE = 100;

    private View Img;//顶部背景图片

    private int ImgHeight;//图片高度

    private int ImgVisibleHeight;//可见高度

    private View contentview;//ScrollView的contestView

    private Rect contntRect=new Rect();//Scroll的矩形

    private float touchdownY;//点击屏幕时Y值

    private boolean abletoTouch=false;//是否关闭滑动

    private boolean ismoving=false;//正在移动

    private boolean isTop=false;//是否是顶部

    private int InitTop, InitBottom;//图片初始时顶部和底部

    private int CurrentTop, CurrentBottom;//图片当前顶部和底部

    private OnTurnListener onturnlistener;//设置变化监听器

    private enum State{ //设定三状态
        UP,DOWN,NORMAL
    }

    private State nowState= State.NORMAL;

    /*三种构造函数*/
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
        setOverScrollMode(OVER_SCROLL_NEVER);//设置滚动效果
        if(attr!=null){
            TypedArray myArray=context.obtainStyledAttributes(attr, R.styleable.PullScrollView);//获取这些属性

            if(myArray !=null) {//有图片属性
                ImgHeight=(int)myArray.getDimension(R.styleable.PullScrollView_ImgHeight,-1);
                ImgVisibleHeight=(int)myArray.getDimension(R.styleable.PullScrollView_ImgVisibleHeight,-1);
                myArray.recycle();//重置
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
    protected void onFinishInflate(){//当findIdBy完成后调用
        if(getChildCount()>0){//至少有一个
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
            doTouchEvent(ev);//当发生了出发触摸，则去处理
        }
        return abletoTouch || super.onTouchEvent(ev);//是否滑动
    }

    private void doTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_MOVE://区别于UP DOWN
                doActionMove(event);//处理动画
                break;

            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {//是否需要动画
                    rollBackAnimation();
                }

                if (getScrollY() == 0) {
                    // 当滚动到顶部时，将状态设置为正常，避免先向上拖动再向下拖动到顶端后首次触摸不响应的问题
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
        // 当滚动到顶部时，将状态设置为正常，避免先向上拖动再向下拖动到顶端后首次触摸不响应的问题
        if (getScrollY() == 0) {
            nowState = State.NORMAL;

            // 滑动经过顶部初始位置时，修正Touch down的坐标为当前Touch点的坐标
            if (isTop) {
                isTop = false;
                touchdownY = event.getY();
            }
        }

        float deltaY = event.getY() - touchdownY;

        // 对于首次Touch操作要判断方位：UP OR DOWN
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

        if (ismoving) {//移动中
            // 初始化content view矩形
            if (contntRect.isEmpty()) {
                // 保存正常的布局位置
                contntRect.set(contentview.getLeft(), contentview.getTop(), contentview.getRight(),
                        contentview.getBottom());
            }

            // 计算Img移动距离(手势移动的距离*阻尼系数*0.5)
            float headerMoveHeight = deltaY * ABOUT_SCROLL;
            CurrentTop = (int) (InitTop + headerMoveHeight);
            CurrentBottom = (int) (InitBottom + headerMoveHeight);

            // 计算content移动距离(手势移动的距离*阻尼系数)
            float contentMoveHeight = deltaY * ABOUT_SCROLL;

            // 修正content移动的距离，避免超过Img的底边缘
            int headerBottom = CurrentBottom - ImgVisibleHeight;
            int top = (int) (contntRect.top + contentMoveHeight);
            int bottom = (int) (contntRect.bottom + contentMoveHeight);

            if (top <= headerBottom && CurrentTop<=InitTop+TURN_DISTANCE) {
                // 移动content view
                contentview.layout(contntRect.left, top, contntRect.right, bottom);

                // 移动header view
                Img.layout(Img.getLeft(), CurrentTop, Img.getRight(), CurrentBottom);
            }
        }
    }

    private void rollBackAnimation() { //上拉回滚动画
        CurrentTop= Math.min(InitTop+TURN_DISTANCE,CurrentTop);
        TranslateAnimation tranAnim = new TranslateAnimation(0, 0,
                Math.abs(InitTop - CurrentTop), 0);//动画
        tranAnim.setDuration(200);
        Img.startAnimation(tranAnim);

        Img.layout(Img.getLeft(), InitTop, Img.getRight(), InitBottom);

        // 开启移动动画
        TranslateAnimation innerAnim = new TranslateAnimation(0, 0, contentview.getTop(), contntRect.top);
        innerAnim.setDuration(200);
        contentview.startAnimation(innerAnim);
        contentview.layout(contntRect.left, contntRect.top, contntRect.right, contntRect.bottom);

        contntRect.setEmpty();

        // 回调监听器
        if (CurrentTop > InitTop + TURN_DISTANCE && onturnlistener != null){
            onturnlistener.onTurn();
        }
    }

    /**
     * 是否需要开启动画
     */
    private boolean isNeedAnimation() {
        return !contntRect.isEmpty() && ismoving;
    }

    public interface  OnTurnListener{
        public void onTurn();
    }
}
