package p007io.reactivex.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* renamed from: io.reactivex.exceptions.CompositeException */
public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List exceptions;
    private final String message;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CompositeException(Throwable... exceptions2) {
        this(exceptions2 == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(exceptions2));
    }

    public CompositeException(Iterable iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<Throwable> localExceptions = new ArrayList<>();
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Throwable ex = (Throwable) it.next();
                if (ex instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) ex).getExceptions());
                } else if (ex != null) {
                    linkedHashSet.add(ex);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (!linkedHashSet.isEmpty()) {
            localExceptions.addAll(linkedHashSet);
            List unmodifiableList = Collections.unmodifiableList(localExceptions);
            this.exceptions = unmodifiableList;
            this.message = unmodifiableList.size() + " exceptions occurred. ";
            return;
        }
        throw new IllegalArgumentException("errors is empty");
    }

    public List getExceptions() {
        return this.exceptions;
    }

    public String getMessage() {
        return this.message;
    }

    public synchronized Throwable getCause() {
        if (this.cause == null) {
            Throwable localCause = new C0256a();
            Set<Throwable> seenCauses = new HashSet<>();
            Throwable chain = localCause;
            for (Throwable e : this.exceptions) {
                if (!seenCauses.contains(e)) {
                    seenCauses.add(e);
                    for (Throwable child : getListOfCauses(e)) {
                        if (seenCauses.contains(child)) {
                            e = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            seenCauses.add(child);
                        }
                    }
                    try {
                        chain.initCause(e);
                    } catch (Throwable th) {
                    }
                    chain = getRootCause(chain);
                }
            }
            this.cause = localCause;
        }
        return this.cause;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream s) {
        printStackTrace(new C0258c(s));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter s) {
        printStackTrace(new C0259d(s));
    }

    private void printStackTrace(AbstractC0257b s) {
        StringBuilder b = new StringBuilder(128);
        b.append(this);
        b.append('\n');
        StackTraceElement[] stackTrace = getStackTrace();
        for (StackTraceElement myStackElement : stackTrace) {
            b.append("\tat ");
            b.append(myStackElement);
            b.append('\n');
        }
        int i = 1;
        for (Throwable ex : this.exceptions) {
            b.append("  ComposedException ");
            b.append(i);
            b.append(" :\n");
            appendStackTrace(b, ex, "\t");
            i++;
        }
        s.mo1084a(b.toString());
    }

    private void appendStackTrace(StringBuilder b, Throwable ex, String prefix) {
        b.append(prefix);
        b.append(ex);
        b.append('\n');
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for (StackTraceElement stackElement : stackTrace) {
            b.append("\t\tat ");
            b.append(stackElement);
            b.append('\n');
        }
        if (ex.getCause() != null) {
            b.append("\tCaused by: ");
            appendStackTrace(b, ex.getCause(), "");
        }
    }

    private List getListOfCauses(Throwable ex) {
        List<Throwable> list = new ArrayList<>();
        Throwable root = ex.getCause();
        if (root == null || root == ex) {
            return list;
        }
        while (true) {
            list.add(root);
            Throwable cause2 = root.getCause();
            if (cause2 == null || cause2 == root) {
                return list;
            }
            root = cause2;
        }
        return list;
    }

    public int size() {
        return this.exceptions.size();
    }

    /* access modifiers changed from: package-private */
    public Throwable getRootCause(Throwable e) {
        Throwable root = e.getCause();
        if (root == null || e == root) {
            return e;
        }
        while (true) {
            Throwable cause2 = root.getCause();
            if (cause2 == null || cause2 == root) {
                return root;
            }
            root = cause2;
        }
        return root;
    }
}
