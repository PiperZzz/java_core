package base.core.basic.classobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class ImmutableClass<T> {
    private final Collection<T> data;

    public ImmutableClass(Collection<T> data) {
        this.data = Collections.unmodifiableCollection(new ArrayList<T>(data));
    }

    public Collection<T> getData() {
        return data;
    }
}
