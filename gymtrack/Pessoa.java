



    package gymtrack;

    public abstract class Pessoa {
        private String nome;
        private int idade;
        private String email;
        private String cpf;
        private String telefone;
        private char sexo;

        public Pessoa(String name, int age, String email, String cpf, String telefone, char sexo) {
            this.nome = name;
            this.idade = age;
            this.email = email;
            this.cpf = cpf;
            this.telefone = telefone;
            this.sexo = sexo;
        }


        public String getName() {
            return nome;
        }

        public void setName(String name) {
            this.nome = name;
        }

        public int getAge() {
            return idade;
        }

        public void setAge(int age) {
            this.idade = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.email = telefone;
        }

        public char getSexo() {
            return sexo;
        }

        public void setSexo(char sexo) {
            this.sexo = sexo;
        }

        // Common method
        public void showInfo() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Email: " + email);
            System.out.println("CPF: " + cpf);
            System.out.println("Telefone: " + telefone);
            System.out.println("Sexo: " + sexo);
        }

        @Override
        public String toString() {
            return
                    "Nome: " + nome + "\n" +
                    "Idade: " + idade + "\n" +
                    "Email: " + email + "\n" +
                    "CPF: " + cpf + "\n" +
                    "Telefone: " + telefone + "\n" +
                    "Sexo: " + sexo + "\n"
                    ;
        }
    }


