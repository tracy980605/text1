package com.mr.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.mr.service.FreshThread;
import com.mr.service.Sound;
/**
 * 恐龙类
 * 
 * @author mingrisoft
 *
 */

public class Dinosaur {
    public BufferedImage image;// 主图片
    private BufferedImage image1, image2, image3;// 跑步图片
    public int x, y;// 坐标
    private int upValue = 0;// 跳跃的增变量
    private int downValue = 0;// 跳跃的增变量
    private boolean upState = false;// 跳跃状态
    private boolean downState = false;// 跳跃状态
    private int flyTimer = 0;// 挥翅膀计时器
    private final int UP_HIGHT = 200;// 跳起最大高度
    private final int DOWN_HIGHT = 200;// 跳起最大高度
    private final int LOWEST_Y = 200 ;// 落地最低坐标
    private final int FREASH = FreshThread.FREASH;// 刷新时间

    public Dinosaur() {
        x = 50;
        y = LOWEST_Y;
        try {
            image1 = ImageIO.read(new File("image/02.png"));
            image2 = ImageIO.read(new File("image/01.png"));
            image3 = ImageIO.read(new File("image/03.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * 移动
	 */
    public void move() {
        step();// 不断挥翅膀
        if (upState) {// 如果正在向上飞
            if (y >= LOWEST_Y) {// 如果纵坐标大于等于最低点
                upValue = -25;// 增变量为负值
            }
            if (y <= LOWEST_Y - UP_HIGHT) {// 如果飞到最高点
                upValue = 25;// 增变量为正值
            }
            y += upValue;// 纵坐标发生变化
           
            if (y > LOWEST_Y-200 ) {// 如果再次落地
            	if (upState) {// 如果正在向上
                    if (y >= LOWEST_Y) {// 如果纵坐标大于等于最低点
                        upValue = -25;// 增变量为负值
                    }
                    if (y <= LOWEST_Y - UP_HIGHT) {// 如果跳过最高点
                        upValue = 25;// 增变量为正值
                    }
                    y += upValue;// 纵坐标发生变化
                    
            	upState = false;// 停止向上
            }
            	
            	if (downState) {// 如果正在向下
                    if (y >= LOWEST_Y) {// 如果纵坐标大于等于最低点
                        downValue = -25;// 增变量为负值
                    }
                    if (y <= LOWEST_Y - UP_HIGHT) {// 如果跳过最高点
                        downValue = 25;// 增变量为正值
                    }
                    y += downValue;// 纵坐标发生变化
                   
                    if (y > LOWEST_Y-200 ) {// 如果再次落地
                    	if (downState) {// 如果正在下降
                            if (y >= LOWEST_Y) {// 如果纵坐标大于等于最低点
                                downValue = -25;// 增变量为负值
                            }
                            if (y <= LOWEST_Y - UP_HIGHT) {// 如果跳过最高点
                                downValue = 25;// 增变量为正值
                            }
                            y += downValue;// 纵坐标发生变化
                            
                    	downState = false;// 停止向下
                    }
                  }
        } 
            }
        }
    }

    /**
     * 跳跃
     * @return 
     */
    public boolean jump() {
        if (!upState) {// 如果没处于跳跃状态
            Sound.jump();// 播放跳跃音效
        }
        return upState = true;
    }
    
    public boolean down() {
        if (!upState) {// 如果没处于向上状态
            Sound.jump();// 播放跳跃音效
        }
        return upState = true;
    }

    /**
     * 踏步
     */
    private void step() {
        // 每过250毫秒，更换一张图片。因为共有3图片，所以除以3取余，轮流展示这三张
        int tmp = flyTimer / 50 % 3;
        switch (tmp) {
            case 1 :
                image = image1;
                break;
            case 2 :
                image = image2;
                break;
            default :
                image = image3;
        }
        flyTimer += FREASH;// 计时器递增
    }

    /**
     * 足部边界区域
     * 
     * @return
     */
    public Rectangle getFootBounds() {
        return new Rectangle(x + 20, y + 49, 19, 8);
    }

    /**
     * 头部边界区域
     * 
     * @return
     */
    public Rectangle getHeadBounds() {
        return new Rectangle(x + 56, y + 15, 22, 12);
    }
}