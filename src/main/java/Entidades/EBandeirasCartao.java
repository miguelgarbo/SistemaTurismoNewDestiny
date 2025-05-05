package Entidades;

public enum EBandeirasCartao {

        VISA("Visa"),
        MASTERCARD("Mastercard"),
        ELO("Elo"),
        AMERICAN_EXPRESS("American Express"),
        HIPERCARD("Hipercard"),
        OUTROS("Outros");

        private final String label;

        EBandeirasCartao(String label) {
                this.label = label;
        }

        public String getLabel() {
                return label;
        }

        @Override
        public String toString() {
                return label;
        }
}