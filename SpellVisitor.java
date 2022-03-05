package com.company;

public interface SpellVisitor <T extends Entity> {
    void visit (T entity);
}
