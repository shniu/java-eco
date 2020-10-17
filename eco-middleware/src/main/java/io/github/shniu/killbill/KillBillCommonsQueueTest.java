package io.github.shniu.killbill;

import org.joda.time.DateTime;
import org.killbill.bus.api.BusEvent;
import org.killbill.bus.api.PersistentBusConfig;
import org.killbill.queue.api.PersistentQueueEntryLifecycleState;
import org.killbill.queue.dao.EventEntryModelDao;
import org.killbill.queue.dispatching.CallableCallback;
import org.killbill.queue.dispatching.Dispatcher;
import org.skife.config.TimeSpan;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author niushaohan
 * @date 2020/9/24 14
 */
public class KillBillCommonsQueueTest {
    static int QUEUE_SIZE = 5;

    public static void main(String[] args) {

        Dispatcher<BusEvent, EventEntryModelDao> dispatcher = new Dispatcher<>(
                1,
                createConfig(),
                5,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(QUEUE_SIZE),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(new ThreadGroup("MatrixEventPoller"), r, "message-relay-poller");
                    }
                },
                null,
                null,
                new CallableCallback<BusEvent, EventEntryModelDao>() {
                    @Override
                    public BusEvent deserialize(EventEntryModelDao modelDao) {
                        return null;
                    }

                    @Override
                    public void dispatch(BusEvent event, EventEntryModelDao modelDao) throws Exception {

                    }

                    @Override
                    public EventEntryModelDao buildEntry(EventEntryModelDao modelDao, DateTime now, PersistentQueueEntryLifecycleState newState, long newErrorCount) {
                        return null;
                    }

                    @Override
                    public void moveCompletedOrFailedEvents(Iterable<EventEntryModelDao> entries) {

                    }

                    @Override
                    public void updateRetriedEvents(EventEntryModelDao updatedEntry) {

                    }
                },
                null);

        dispatcher.start();
    }

    private static PersistentBusConfig createConfig() {
        return new PersistentBusConfig() {
            @Override
            public boolean isInMemory() {
                return false;
            }

            @Override
            public int getMaxFailureRetries() {
                return 0;
            }

            @Override
            public int getMinInFlightEntries() {
                return 1;
            }

            @Override
            public int getMaxInFlightEntries() {
                return 1;
            }

            @Override
            public int getMaxEntriesClaimed() {
                return 1;
            }

            @Override
            public PersistentQueueMode getPersistentQueueMode() {
                return PersistentQueueMode.STICKY_EVENTS;
            }

            @Override
            public TimeSpan getClaimedTime() {
                return new TimeSpan("5m");
            }

            @Override
            public long getPollingSleepTimeMs() {
                return 100;
            }

            @Override
            public boolean isProcessingOff() {
                return false;
            }

            @Override
            public int geMaxDispatchThreads() {
                return 1;
            }

            @Override
            public int geNbLifecycleDispatchThreads() {
                return 1;
            }

            @Override
            public int geNbLifecycleCompleteThreads() {
                return 1;
            }

            @Override
            public int getEventQueueCapacity() {
                return QUEUE_SIZE;
            }

            @Override
            public String getTableName() {
                return "bus_events";
            }

            @Override
            public String getHistoryTableName() {
                return "bus_events_history";
            }

            @Override
            public TimeSpan getReapThreshold() {
                return new TimeSpan(5, TimeUnit.MINUTES);
            }

            @Override
            public int getMaxReDispatchCount() {
                return 10;
            }

            @Override
            public TimeSpan getReapSchedule() {
                return new TimeSpan(3, TimeUnit.MINUTES);
            }
        };
    }
}
