package src;

import java.util.ArrayList;

/**
 * <p>Title: FCFSScheduler</p>
 * <p>Description: Component of the simulate operating system that encapsulates FCFS job scheduling.</p>
 * <p>Copyright: Copyright (c) 2015, 2004</p>
 * <p>Company: </p>
 * @author Matt Evett
 * @version 2.0
 */

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FCFSScheduler extends Scheduler {

	/*
	 * TO_DO: your data structure to support a FCFS scheduler and the abstract
	 * methods of Scheduler
	 */

	// Can't I just stack up an arrayList and pull from the head?
	ArrayList<Job> queue;

	Condition waiting;

	public FCFSScheduler() {
		this.queue = new ArrayList<Job>();

		this.waiting = new ReentrantLock().newCondition();
	}

	/**
	 * If the ready queue is empty, return false. Otherwise, start the next job in
	 * the queue, returning true. If the queue is empty return false. Make the next
	 * job in the ready queue run. You should probably invoke Thread.start() on it.
	 */
	public boolean makeRun() {
		// System.out.println("TO_DO: makeRun not yet implemented");
		System.out.println("TO_DO: makeRun in progress");

		/*
		 * Place code here that gets the next Job from the ready queue and invokes
		 * start() on it
		 *
		 */

		if (queue.size() == 0) {
			return false;
		} else {
			currentlyRunningJob = queue.get(0);
			this.remove(currentlyRunningJob);
			currentlyRunningJob.start();
			return true; // TO_DO ***SHOULDN'T ALWAYS RETURN TRUE***
		}
	}

	/**
	 * blockTilThereIsAJob() Invoked by OS simulator when it wants to get a new Job
	 * to run. Will block if the ready queue is empty until a Job is added to the
	 * queue.
	 */
	public void blockTilThereIsAJob() {
		if (hasRunningJob()) {
			return;
		}
		// System.out.println("TO_DO: blockTilThereIsAJob not yet implemented");
		System.out.println("TO_DO: blockTilThereIsAJob in progress");

		/*
		 * Place code here that will cause the calling thread to block until the ready
		 * queue contains a Job
		 */
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		// This would work if you knew when there were no more jobs to run.
//		while (this.queue.size() == 0) {
//		}
		synchronized (this.waiting) {
			try {
				this.waiting.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("evidently there is now a job on readyQ");
	}

	@Override
	public void add(Job J) {
		this.queue.add(J);
		synchronized (this.waiting) {
			this.waiting.signal();
		}
	}

	@Override
	public void remove(Job J) {
		this.queue.remove(J);
	}

	@Override
	public boolean hasJobsQueued() {
		if (this.queue.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
