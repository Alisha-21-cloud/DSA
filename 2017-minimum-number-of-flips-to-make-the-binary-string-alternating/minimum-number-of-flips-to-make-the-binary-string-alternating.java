class Solution {
    public int minFlips(String s) {
        int n = s.length();
        // Concatenate string to handle cyclic shifts (Type-1)
        String doubled = s + s;
        
        int opsForZero = 0, opsForOne = 0;
        int minOps = Integer.MAX_VALUE;

        for (int i = 0; i < doubled.length(); i++) {
            // Pattern 1: Starts with '0' (0101...)
            // If even index should be '0' but isn't, OR odd index should be '1' but isn't
            if ((i % 2 == 0 && doubled.charAt(i) != '0') || (i % 2 != 0 && doubled.charAt(i) != '1')) {
                opsForZero++;
            }
            
            // Pattern 2: Starts with '1' (1010...)
            // If even index should be '1' but isn't, OR odd index should be '0' but isn't
            if ((i % 2 == 0 && doubled.charAt(i) != '1') || (i % 2 != 0 && doubled.charAt(i) != '0')) {
                opsForOne++;
            }

            // Once our window reaches the original length 'n'
            if (i >= n) {
                // Remove the effect of the character that just left the window (at index i - n)
                int leftIdx = i - n;
                
                // Subtract from opsForZero if the departing char mismatched Pattern 1
                if ((leftIdx % 2 == 0 && doubled.charAt(leftIdx) != '0') || (leftIdx % 2 != 0 && doubled.charAt(leftIdx) != '1')) {
                    opsForZero--;
                }
                
                // Subtract from opsForOne if the departing char mismatched Pattern 2
                if ((leftIdx % 2 == 0 && doubled.charAt(leftIdx) != '1') || (leftIdx % 2 != 0 && doubled.charAt(leftIdx) != '0')) {
                    opsForOne--;
                }
            }

            // If we have a full window of size n, track the minimum
            if (i >= n - 1) {
                minOps = Math.min(minOps, Math.min(opsForZero, opsForOne));
            }
        }

        return minOps;
    }
}