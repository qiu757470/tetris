package org.great.threadtest;

/*
 * ʵ�����̴߳�ӡ10�Σ����̴߳�ӡ��󣬽������̴߳�ӡ20�Σ����̴߳�ӡ��֮�󣬽������̴߳�ӡ10�Σ���˷���ִ�У����߳����̸߳���50�Ρ�
 * ����ʾ��ʹ���̵߳ȴ����ѻ�����������
 */
public class Test {

	public static void main(String[] args) {
		PrintHandle handle = new PrintHandle();
		
		SonThread son = new SonThread(handle);
		son.setName("SonThread");
		son.start();
		
		for(int i = 0; i < 50; i++){
			handle.mainThread();
		}
	}
}
