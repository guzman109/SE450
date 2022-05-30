package shop.ui;

enum UIFormTestEnum implements UIFormTest{
    YEAR {
        public boolean run(String input) {
            try {
                int i = Integer.parseInt(input);
                return i > 1800 && i < 5000;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    NUMBER {       
        public boolean run(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    STRING {
        public boolean run(String input) {
            return ! "".equals(input.trim());
        }
    };
}
