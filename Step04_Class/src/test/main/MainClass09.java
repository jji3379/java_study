package test.main;

import test.mart.Computer;
import test.mart.Cpu;
import test.mart.HardDisk;
import test.mart.Memory;

public class MainClass09 {
	public static void main(String[] args) {
		/*
		 *  [프로그래밍 목적]
		 * 
		 * 	1. 게임을 한다.
		 * 	2. oli999@naver.com 으로 이메일을 보낸다.
		 */
		Cpu cpu=new Cpu();
		Memory memory=new Memory();
		HardDisk harddisk=new HardDisk();
		
		Computer computer=new Computer(cpu, memory, harddisk);
		computer.playGame();
		computer.sendEmail("oli999@naver.com");
	}
}
