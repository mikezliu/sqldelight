package com.alecstrong.sqlite.android.psi;

import com.alecstrong.sqlite.android.SQLiteLexer;
import com.alecstrong.sqlite.android.lang.SqliteASTFactory;
import com.alecstrong.sqlite.android.lang.SqliteTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class TableNameElement extends SqliteElement {

  public TableNameElement(@NotNull ASTNode node) {
    super(node);
  }

  @Override public IdentifierElement getId() {
    return PsiTreeUtil.findChildOfType(this, IdentifierElement.class);
  }

  public boolean isSameTable(TableNameElement other) {
    if (other == null) return false;
    IdentifierElement id = getId();
    IdentifierElement otherId = other.getId();
    if (id == null || otherId == null) return false;
    String name = id.getName();
    String otherName = otherId.getName();
    return name != null && otherName != null && name.equals(otherName);
  }

  @Override public IElementType getRuleRefType() {
    return SqliteTokenTypes.TOKEN_ELEMENT_TYPES.get(SQLiteLexer.IDENTIFIER);
  }

  public static class Factory implements SqliteASTFactory.PsiElementFactory {
    public static final Factory INSTANCE = new Factory();

    @Override public PsiElement createElement(ASTNode node) {
      return new TableNameElement(node);
    }
  }
}
