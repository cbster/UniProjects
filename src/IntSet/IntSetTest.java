package IntSet;

class IntSetTest {

	static boolean handleException = false;

	private static void trace(String t) {
		System.out.println(t);
	}

	public static void main(String s[]) throws SetError {

		if (s.length > 0 && s[0].equals("-h"))
			handleException = true;

		IntSet s1 = new IntSet(10);
		IntSet s2 = new IntSet(20);

		s1.include(5);
		trace("s1.complement() = " + s1.complement());
		s2.include(15);
		trace("s2.complement() = " + s2.complement());

		trace("s1 = " + s1);
		trace("s2 = " + s2);

		s1=s1.enlarge(15);
		trace("s1 = " + s1);
		trace("s1.bits() = " + s1.bits());
		trace("s1.complement() = " + s1.complement());
		trace("s1.complement().bits() = " + s1.complement().bits());

		IntSet s3 = IntSet.union(s1, s2);
		trace("s3 = " + s3);
		trace("s3.capacity() = " + s3.capacity());                       
		trace("s3.complement() = " + s3.complement());

		IntSet s4 = new IntSet(30);
		trace("s4 = " + s4);
		trace("s4.capacity() = " + s4.capacity());

		s4=IntSet.union(s4, s3);
		trace("s4 = " + s4);
		s4=s4.complement();
		trace("s4 = " + s4);

		IntSet s5 = new IntSet(10);
		for (IntSet.Iterator it = s4.iterator(); it.hasNext(); ) {
			int i = it.next();
			if (i%7==0)
				System.out.println(i);
			else s5.include (i%7);
		}
		trace("s5 = " + s5);

		trace("IntSet.difference(s4, s5) = " + IntSet.difference(s4, s5));

		int v[] = { 19, 17, 11, 13, 2, 3, 5, 7 };
		IntSet s6 = new IntSet(20);
		s6.include(v);
		trace("s6 = " + s6);
		trace("s6.bits() = " + s6.bits());

		if (!handleException)
			s6.include(24); // don't handle
		else
			try {
				s6.include(24);
			} catch(SetError se) {
				se.printStackTrace(System.out);
			}
		System.out.println("regular program end");
	}
}

