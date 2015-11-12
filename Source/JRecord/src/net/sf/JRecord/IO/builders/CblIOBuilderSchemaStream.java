package net.sf.JRecord.IO.builders;

import java.io.IOException;
import java.io.InputStream;

import net.sf.JRecord.External.ExternalRecord;
import net.sf.JRecord.External.ICopybookLoaderStream;
import net.sf.JRecord.External.ISetDropCopybookName;

public class CblIOBuilderSchemaStream extends CblIOBuilderBase {

	private final InputStream copybookStream;
	private final String copybookName;
	private final ICopybookLoaderStream loader;

	
	/**
	 * Create a IOBuilder for a Stream based Copybook (schema)
	 * 
	 * @param copybookStream copybook stream
	 * @param copybookName name of the copybook
	 * @param copybookType copybook type
	 * @param cobolDialect Cobol Dialect
	 */
	public CblIOBuilderSchemaStream(InputStream copybookStream, String copybookName, ICopybookLoaderStream loader, int cobolDialect) {
		super(cobolDialect);
		this.copybookName = copybookName;
		this.copybookStream = copybookStream;
		this.loader = loader;
	} 

	/* (non-Javadoc)
	 * @see net.sf.JRecord.IO.builders.CblIOBuilderBase#getExternalRecordImpl()
	 */
	@Override
	protected ExternalRecord getExternalRecordImpl() throws IOException {
		if (loader instanceof ISetDropCopybookName) {
			((ISetDropCopybookName) loader).setDropCopybookFromFieldNames(super.dropCopybookNameFromFields);
		}
						
		return  loader	.loadCopyBook(copybookStream, copybookName, splitCopybook, 0, super.getFont(), super.copybookFileFormat, dialect, 0, log);
	}

}
