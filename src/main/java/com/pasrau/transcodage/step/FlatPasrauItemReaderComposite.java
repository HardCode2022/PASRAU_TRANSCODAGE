package com.pasrau.transcodage.step;

import com.pasrau.transcodage.model.FlatPasrauItemReader;
import org.springframework.batch.item.ItemReader;

public class FlatPasrauItemReaderComposite implements ItemReader<FlatPasrauItemReader> {

    private final FlatPasrauItemReader[] readers;
    private int currentReaderIndex = 0;

    public FlatPasrauItemReaderComposite(FlatPasrauItemReader[] readers) {
        this.readers = readers;
    }

    @Override
    public FlatPasrauItemReader read() {
        if (currentReaderIndex < readers.length) {
            FlatPasrauItemReader currentReader = readers[currentReaderIndex++];
            return currentReader;
        } else {
            return null;
        }
    }
}
