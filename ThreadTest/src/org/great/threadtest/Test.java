package org.great.threadtest;

/*
 * 实现子线程打印10次，子线程打印完后，接着主线程打印20次，主线程打印完之后，接着子线程打印10次，如此反复执行，主线程子线程各打50次。
 * （提示：使用线程等待唤醒机制来做。）
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
