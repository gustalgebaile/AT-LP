package org.example.domain.entities;

public final class Delivery {
    private final String address;
    private final double weight;
    private final ShippingType shippingType;
    private final String recipient;

    public Delivery(String address, double weight, ShippingType shippingType, String recipient) {
        validateInputs(address, weight, shippingType, recipient);
        this.address = address;
        this.weight = weight;
        this.shippingType = shippingType;
        this.recipient = recipient;
    }

    private void validateInputs(String address, double weight, ShippingType shippingType, String recipient) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode está nulo ou vazio");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Peso não pode ser negativo");
        }
        if (shippingType == null) {
            throw new IllegalArgumentException("Tipo de Frete não pode está vazio");
        }
        if (recipient == null || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("Destinatário não pode está nulo ou vazio");
        }
    }

    public String getAddress() { return address; }
    public double getWeight() { return weight; }
    public ShippingType getShippingType() { return shippingType; }
    public String getRecipient() { return recipient; }
}
