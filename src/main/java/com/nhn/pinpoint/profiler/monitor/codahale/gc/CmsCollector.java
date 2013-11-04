package com.nhn.pinpoint.profiler.monitor.codahale.gc;

import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_GC_CMS_COUNT;
import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_GC_CMS_TIME;
import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_MEMORY_HEAP_MAX;
import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_MEMORY_HEAP_USED;
import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_MEMORY_NONHEAP_MAX;
import static com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues.JVM_MEMORY_NONHEAP_USED;

import com.codahale.metrics.MetricRegistry;
import com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorRegistry;
import com.nhn.pinpoint.profiler.monitor.codahale.MetricMonitorValues;
import com.nhn.pinpoint.thrift.dto.TAgentStat;
import com.nhn.pinpoint.thrift.dto.TJvmGc;
import com.nhn.pinpoint.thrift.dto.TJvmGcType;

/**
 * HotSpot's Concurrent-Mark-Sweep collector
 * 
 * @author harebox
 */
public class CmsCollector extends GarbageCollectorType {

	@Override
	public int getTypeCode() {
		return GarbageCollectorType.CMS_COLLECTOR;
	}

	@Override
	public void map(MetricMonitorRegistry registry, TAgentStat agentStat, String agentId) {
		MetricRegistry r = registry.getRegistry();
		TJvmGc gc = agentStat.getGc();
		if (gc == null) {
			gc = new TJvmGc();
			agentStat.setGc(gc);
		}
		gc.setType(TJvmGcType.CMS);
		gc.setJvmMemoryHeapMax(MetricMonitorValues.getLong(r, JVM_MEMORY_HEAP_MAX));
		gc.setJvmMemoryHeapUsed(MetricMonitorValues.getLong(r, JVM_MEMORY_HEAP_USED));
		gc.setJvmMemoryNonHeapMax(MetricMonitorValues.getLong(r, JVM_MEMORY_NONHEAP_MAX));
		gc.setJvmMemoryNonHeapUsed(MetricMonitorValues.getLong(r, JVM_MEMORY_NONHEAP_USED));
		gc.setJvmGcOldCount(MetricMonitorValues.getLong(r, JVM_GC_CMS_COUNT));
		gc.setJvmGcOldTime(MetricMonitorValues.getLong(r, JVM_GC_CMS_TIME));
	}

	@Override
	public String toString() {
		return "HotSpot's Concurrent-Mark-Sweep collector";
	}
	
}
