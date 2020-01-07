package com.mr.modle;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.mr.service.FreshThread;
import com.mr.service.Sound;
/**
 * ������
 * 
 * @author mingrisoft
 *
 */

public class Dinosaur {
    public BufferedImage image;// ��ͼƬ
    private BufferedImage image1, image2, image3;// �ܲ�ͼƬ
    public int x, y;// ����
    private int upValue = 0;// ��Ծ��������
    private int downValue = 0;// ��Ծ��������
    private boolean upState = false;// ��Ծ״̬
    private boolean downState = false;// ��Ծ״̬
    private int flyTimer = 0;// �ӳ���ʱ��
    private final int UP_HIGHT = 200;// �������߶�
    private final int DOWN_HIGHT = 200;// �������߶�
    private final int LOWEST_Y = 200 ;// ����������
    private final int FREASH = FreshThread.FREASH;// ˢ��ʱ��

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
	 * �ƶ�
	 */
    public void move() {
        step();// ���ϻӳ��
        if (upState) {// ����������Ϸ�
            if (y >= LOWEST_Y) {// �����������ڵ�����͵�
                upValue = -25;// ������Ϊ��ֵ
            }
            if (y <= LOWEST_Y - UP_HIGHT) {// ����ɵ���ߵ�
                upValue = 25;// ������Ϊ��ֵ
            }
            y += upValue;// �����귢���仯
           
            if (y > LOWEST_Y-200 ) {// ����ٴ����
            	if (upState) {// �����������
                    if (y >= LOWEST_Y) {// �����������ڵ�����͵�
                        upValue = -25;// ������Ϊ��ֵ
                    }
                    if (y <= LOWEST_Y - UP_HIGHT) {// ���������ߵ�
                        upValue = 25;// ������Ϊ��ֵ
                    }
                    y += upValue;// �����귢���仯
                    
            	upState = false;// ֹͣ����
            }
            	
            	if (downState) {// �����������
                    if (y >= LOWEST_Y) {// �����������ڵ�����͵�
                        downValue = -25;// ������Ϊ��ֵ
                    }
                    if (y <= LOWEST_Y - UP_HIGHT) {// ���������ߵ�
                        downValue = 25;// ������Ϊ��ֵ
                    }
                    y += downValue;// �����귢���仯
                   
                    if (y > LOWEST_Y-200 ) {// ����ٴ����
                    	if (downState) {// ��������½�
                            if (y >= LOWEST_Y) {// �����������ڵ�����͵�
                                downValue = -25;// ������Ϊ��ֵ
                            }
                            if (y <= LOWEST_Y - UP_HIGHT) {// ���������ߵ�
                                downValue = 25;// ������Ϊ��ֵ
                            }
                            y += downValue;// �����귢���仯
                            
                    	downState = false;// ֹͣ����
                    }
                  }
        } 
            }
        }
    }

    /**
     * ��Ծ
     * @return 
     */
    public boolean jump() {
        if (!upState) {// ���û������Ծ״̬
            Sound.jump();// ������Ծ��Ч
        }
        return upState = true;
    }
    
    public boolean down() {
        if (!upState) {// ���û��������״̬
            Sound.jump();// ������Ծ��Ч
        }
        return upState = true;
    }

    /**
     * ̤��
     */
    private void step() {
        // ÿ��250���룬����һ��ͼƬ����Ϊ����3ͼƬ�����Գ���3ȡ�࣬����չʾ������
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
        flyTimer += FREASH;// ��ʱ������
    }

    /**
     * �㲿�߽�����
     * 
     * @return
     */
    public Rectangle getFootBounds() {
        return new Rectangle(x + 20, y + 49, 19, 8);
    }

    /**
     * ͷ���߽�����
     * 
     * @return
     */
    public Rectangle getHeadBounds() {
        return new Rectangle(x + 56, y + 15, 22, 12);
    }
}