package com.telus.channelsalesmgmt.common.monitoring;

import org.springframework.util.StopWatch;

public class FinishedWork implements Comparable<FinishedWork> {

	private StopWatch stopWatch;
	private Integer workIndex;

	public FinishedWork(int workIndex, StopWatch sw) {
		this.workIndex = workIndex;
		this.stopWatch = sw;
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	@Override
	public int compareTo(FinishedWork other) {
		return this.workIndex.compareTo(other.workIndex);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stopWatch == null) ? 0 : stopWatch.hashCode());
		result = prime * result + ((workIndex == null) ? 0 : workIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinishedWork other = (FinishedWork) obj;
		if (stopWatch == null) {
			if (other.stopWatch != null)
				return false;
		} else if (!stopWatch.equals(other.stopWatch))
			return false;
		if (workIndex == null) {
			if (other.workIndex != null)
				return false;
		} else if (!workIndex.equals(other.workIndex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinishedWork [stopWatch=" + stopWatch + "]";
	}

}
