package com.myapp.other.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import com.myapp.other.SimpleData;
import wickettree.ITreeProvider;
import wickettree.util.IntermediateTreeProvider;
import wickettree.util.SortableTreeProvider;

/**
 *
 * @author Posudevskiy
 */
public class SimpleDataProvider implements ITreeProvider<SimpleData> {

    private static List<SimpleData> roots = new ArrayList<SimpleData>();

    static {
        SimpleData simpleDataA = new SimpleData("09.07.2012");
        {
            SimpleData simpleDataAA = new SimpleData(simpleDataA, "parking", "12.03", "12.13",
                    "1d 0h 10m", "---", "---");
            SimpleData simpleDataAB = new SimpleData(simpleDataA, "move", "12.13", "12.23",
                    "2d 0h 10m", "60km/h", "10km");
            SimpleData simpleDataAC = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBD = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBE = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBF = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "--", "---");
            SimpleData simpleDataBG = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBH = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBJ = new SimpleData(simpleDataA, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
        }
        roots.add(simpleDataA);
        SimpleData simpleDataB = new SimpleData("10.07.2012");

        {
            SimpleData simpleDataBA = new SimpleData(simpleDataB, "parking", "12.03", "12.13",
                    "1d 0h 10m", "---", "---");
            SimpleData simpleDataBB = new SimpleData(simpleDataB, "move", "12.13", "12.23",
                    "2d 0h 10m", "60km/h", "10km");
            SimpleData simpleDataBC = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBD = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBE = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBF = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "--", "---");
            SimpleData simpleDataBG = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBH = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
            SimpleData simpleDataBJ = new SimpleData(simpleDataB, "parking", "12.23", "12.33",
                    "3d 0h 10m", "---", "---");
        }
        roots.add(simpleDataB);

    }
    private boolean intermediate;

    public SimpleDataProvider() {
        this(false);
    }

    public SimpleDataProvider(boolean intermediate) {
        this.intermediate = intermediate;
    }

    public void detach() {
    }

    public Iterator<SimpleData> getRoots() {
        return roots.iterator();
    }

    public boolean hasChildren(SimpleData foo) {
        return foo.getParent() == null || !foo.getRecords().isEmpty();
    }

    public Iterator<SimpleData> getChildren(final SimpleData foo) {
        if (intermediate) {
            if (!foo.isLoaded()) {
                asynchronuous(new Runnable() {

                    public void run() {
                        foo.setLoaded(true);
                    }
                });

                // mark children intermediate
                return IntermediateTreeProvider.intermediate(Collections.<SimpleData>emptyList().iterator());
            }
        }

        return foo.getRecords().iterator();
    }

    private void asynchronuous(Runnable runnable) {
        runnable.run();
    }

    public static void resetLoaded() {
        for (SimpleData foo : roots) {
            resetLoaded(foo);
        }
    }

    private static void resetLoaded(SimpleData foo) {
        foo.setLoaded(false);

        for (SimpleData child : foo.getRecords()) {
            resetLoaded(child);
        }
    }

    public IModel<SimpleData> model(SimpleData foo) {
        return new SimpleDataProvider.SimpleDataModel(foo);
    }

    public static SimpleData get(String id) {
        return get(roots, id);
    }

    private static SimpleData get(List<SimpleData> foos, String id) {
        for (SimpleData foo : foos) {
            if (foo.getId().equals(id)) {
                return foo;
            }

            SimpleData temp = get(foo.getRecords(), id);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    private static class SimpleDataModel extends LoadableDetachableModel<SimpleData> {

        private static final long serialVersionUID = 1L;
        private String id;

        public SimpleDataModel(SimpleData foo) {
            super(foo);

            id = foo.getId();
        }

        @Override
        protected SimpleData load() {
            return get(id);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SimpleDataProvider.SimpleDataModel) {
                return ((SimpleDataProvider.SimpleDataModel) obj).id.equals(this.id);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
