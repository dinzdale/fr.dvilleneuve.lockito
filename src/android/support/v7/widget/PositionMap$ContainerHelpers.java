package android.support.v7.widget;

class PositionMap$ContainerHelpers
{
  static final boolean[] EMPTY_BOOLEANS = new boolean[0];
  static final int[] EMPTY_INTS = new int[0];
  static final long[] EMPTY_LONGS = new long[0];
  static final Object[] EMPTY_OBJECTS = new Object[0];
  
  static int binarySearch(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt1 - 1;
    paramInt1 = i;
    i = j;
    while (paramInt1 <= i)
    {
      j = paramInt1 + i >>> 1;
      int k = paramArrayOfInt[j];
      if (k < paramInt2)
      {
        paramInt1 = j + 1;
      }
      else
      {
        i = j;
        if (k <= paramInt2) {
          return i;
        }
        i = j - 1;
      }
    }
    i = paramInt1 ^ 0xFFFFFFFF;
    return i;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.PositionMap.ContainerHelpers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */