package io.github.shniu.arts.sourcecode;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    @Test
    public void testAtomicStampedReference() {
        String str1 = "aaa";
        String str2 = "bbb";

        AtomicStampedReference<String> reference = new AtomicStampedReference<>(str1, 1);
        reference.compareAndSet(str1, str2, reference.getStamp(), reference.getStamp() + 1);
        assert reference.getReference().equals(str2);

        boolean b = reference.attemptStamp(str2, reference.getStamp() + 1);
        assert b;
        assert reference.getStamp() == 3;

        boolean updated = reference.weakCompareAndSet(str2, "ccc", 4, reference.getStamp() + 1);
        assert !updated;
        assert reference.getReference().equals(str2);
    }
}
