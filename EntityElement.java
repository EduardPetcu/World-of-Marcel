package com.company;
// Step 1
public interface EntityElement <T extends Entity> {
    void accept(SpellVisitor <T> visitor);
}
