package ma.adam;

    public class EntierNaturel {
        private int val;

        public EntierNaturel(int val) throws NombreNegatifException {
            if (val < 0) {
                throw new NombreNegatifException("Negative value provided during initialization", val);
            }
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) throws NombreNegatifException {
            if (val < 0) {
                throw new NombreNegatifException("Negative value provided in setVal", val);
            }
            this.val = val;
        }

        public void decrementer() throws NombreNegatifException {
            if (val - 1 < 0) {
                throw new NombreNegatifException("Decrement would result in a negative value", val - 1);
            }
            val--;
        }
    }



