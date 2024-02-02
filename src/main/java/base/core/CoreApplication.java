package base.core;

import java.util.*;

import java.util.concurrent.ConcurrentHashMap;

import base.core.leetcode.ListNode;

public class CoreApplication {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "1");
	}

    public static void printListNode() {
        ArrayList<ListNode> listNode = new ArrayList<>();
        System.out.println(listNode);
    }
}