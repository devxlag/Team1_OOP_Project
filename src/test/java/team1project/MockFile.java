package team1project;

class MockFile implements AbstractFile {
        private String fileName;

        public MockFile(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            // Mock display method
        }
    }
