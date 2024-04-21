package com.kingsleyohas.allstay24backoffice.entity;
import jakarta.persistence.*;

    @Entity
    @Table(name="property_type")
    public class PropertyType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private int id;

        @Column(name="type_name")
        private String typeName;


        public PropertyType() {
        }

        public PropertyType(String typeName) {
            this.typeName = typeName;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getTypeName() {
            return typeName;
        }


        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }


        @Override
        public String toString() {
            return "PropertyType{" +
                    "id=" + id +
                    ", typeName='" + typeName + '\'' +
                    '}';
        }

    }


